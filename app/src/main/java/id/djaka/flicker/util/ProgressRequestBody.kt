package id.djaka.flicker.util

import android.os.Handler
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.IOException
import android.os.Looper
import java.io.FileInputStream


open class ProgressRequestBody(file: File, content_type: String, listener: UploadCallbacks) : RequestBody() {
    private var mFile: File? = null
    private var mPath: String? = null
    private var mListener: UploadCallbacks? = null
    private var contentType: String? = null

    init {
        this.contentType = content_type
        mFile = file
        mListener = listener
    }


    override fun contentType(): MediaType? {
        return MediaType.parse("$contentType/*");
    }

    @Throws(IOException::class)
    override fun contentLength(): Long {
        return mFile!!.length()
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        val fileLength = mFile!!.length()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val file = FileInputStream(mFile)
        var uploaded: Long = 0

        file.use {
            var read = file.read(buffer)
            val handler = Handler(Looper.getMainLooper())
            while (read != -1) {
                // update progress on UI thread
                uploaded += read.toLong()
                sink.write(buffer, 0, read)
                read = file.read(buffer)
                handler.post(ProgressUpdater(uploaded, fileLength))
            } }
    }

    interface UploadCallbacks {
        fun onProgressUpdate(percentage: Int)
        fun onError()
        fun onFinish()
    }

    inner class ProgressUpdater(uploaded: Long, total: Long) : Runnable {
        private var mUploaded: Long = 0
        private var mTotal: Long = 0

        init {
            mUploaded = uploaded
            mTotal = total
        }

        override fun run() {
            mListener!!.onProgressUpdate((100 * mUploaded / mTotal).toInt())
            if((100 * mUploaded / mTotal).toInt() == 100)
                mListener!!.onFinish()
        }
    }
}