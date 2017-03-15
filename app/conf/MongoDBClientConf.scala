package conf

import com.mongodb.MongoCredential
import play.api.Play.current

object MongoDBClientConf {
  val db = current.configuration.getString("mongodb.default.db")
  val host = current.configuration.getString("mongodb.default.host")
  val port = current.configuration.getString("mongodb.default.port")
  val user = current.configuration.getString("mongodb.default.user")
  val password = current.configuration.getString("mongodb.default.password")
  val authMethod = current.configuration.getString("mongodb.auth.method")

  def getDefaultMongoClientSetting(): String = {
    user + ":" + password + "@" + host + ":" + port + "/?authSource=" + db + "&authMechanism=" + authMethod
  }
  def getMongoCredentialList(): List[MongoCredential] = {
    List(
      MongoCredential
        .createScramSha1Credential(user.get, db.get, password.get.toCharArray))
  }
}
