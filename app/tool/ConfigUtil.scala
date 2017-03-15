package tool

import com.mongodb.ServerAddress
import play.api.Play

/**
  * Created by loui.wang on 2017/3/9.
  */
object ConfigUtil {
  def get(path: String): String = {
    Play.current.configuration.getString(path).get
  }

  def getInt(path: String): Int = {
    Play.current.configuration.getString(path).get.toInt
  }

  def getMongoDbServerAddress() = {
    new ServerAddress(get("mongodb.default.host"),
                      getInt("mongodb.default.port"))
  }
}
