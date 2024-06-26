// Autogenerated from Pigeon (v17.3.0), do not edit directly.
// See also: https://pub.dev/packages/pigeon


import android.util.Log
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.common.StandardMessageCodec
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

private fun wrapResult(result: Any?): List<Any?> {
  return listOf(result)
}

private fun wrapError(exception: Throwable): List<Any?> {
  if (exception is WakelockPlusFlutterError) {
    return listOf(
      exception.code,
      exception.message,
      exception.details
    )
  } else {
    return listOf(
      exception.javaClass.simpleName,
      exception.toString(),
      "Cause: " + exception.cause + ", Stacktrace: " + Log.getStackTraceString(exception)
    )
  }
}

/**
 * Error class for passing custom error details to Flutter via a thrown PlatformException.
 * @property code The error code.
 * @property message The error message.
 * @property details The error details. Must be a datatype supported by the api codec.
 */
class WakelockPlusFlutterError (
  val code: String,
  override val message: String? = null,
  val details: Any? = null
) : Throwable()

/**
 * Message for toggling the wakelock on the platform side.
 *
 * Generated class from Pigeon that represents data sent in messages.
 */
data class ToggleMessage (
  val enable: Boolean? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): ToggleMessage {
      val enable = list[0] as Boolean?
      return ToggleMessage(enable)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      enable,
    )
  }
}

/**
 * Message for reporting the wakelock state from the platform side.
 *
 * Generated class from Pigeon that represents data sent in messages.
 */
data class IsEnabledMessage (
  val enabled: Boolean? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): IsEnabledMessage {
      val enabled = list[0] as Boolean?
      return IsEnabledMessage(enabled)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      enabled,
    )
  }
}
@Suppress("UNCHECKED_CAST")
private object WakelockPlusApiCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      128.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          IsEnabledMessage.fromList(it)
        }
      }
      129.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          ToggleMessage.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?)   {
    when (value) {
      is IsEnabledMessage -> {
        stream.write(128)
        writeValue(stream, value.toList())
      }
      is ToggleMessage -> {
        stream.write(129)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/** Generated interface from Pigeon that represents a handler of messages from Flutter. */
interface WakelockPlusApi {
  fun toggle(msg: ToggleMessage)
  fun isEnabled(): IsEnabledMessage

  companion object {
    /** The codec used by WakelockPlusApi. */
    val codec: MessageCodec<Any?> by lazy {
      WakelockPlusApiCodec
    }
    /** Sets up an instance of `WakelockPlusApi` to handle messages through the `binaryMessenger`. */
    @Suppress("UNCHECKED_CAST")
    fun setUp(binaryMessenger: BinaryMessenger, api: WakelockPlusApi?) {
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.wakelock_plus_platform_interface.WakelockPlusApi.toggle", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val msgArg = args[0] as ToggleMessage
            var wrapped: List<Any?>
            try {
              api.toggle(msgArg)
              wrapped = listOf<Any?>(null)
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.wakelock_plus_platform_interface.WakelockPlusApi.isEnabled", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            var wrapped: List<Any?>
            try {
              wrapped = listOf<Any?>(api.isEnabled())
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
    }
  }
}
