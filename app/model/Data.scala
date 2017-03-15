package model

/**
  * Created by loui.wang on 2017/3/9.
  */
import com.mongodb.casbah.commons.MongoDBObject
import conf.MongoDBClientConf
import org.bson.types.ObjectId
import play.api.PlayException
import salat.annotations.Key
import salat.dao.SalatDAO
import com.mongodb.casbah.Imports._
import play.api.Play.current
import tool.ConfigUtil
import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.commons.conversions.scala.{
  RegisterConversionHelpers,
  RegisterJodaTimeConversionHelpers
}
import tool.MongoContext._

case class Data(@Key("_id") id: Any = new ObjectId, data: Any)

object DataDAO
    extends SalatDAO[Data, ObjectId](
      MongoClient(ConfigUtil.getMongoDbServerAddress(),
                  MongoDBClientConf.getMongoCredentialList())(
        current.configuration
          .getString("mongodb.default.db")
          .getOrElse(throw new PlayException(
            "Configuration error",
            "Could not find mongodb.default.db in settings"))
      )("data"))
object Data {
  RegisterConversionHelpers()
  RegisterJodaTimeConversionHelpers()

  def add(data: Any) = {
    val id = DataDAO.insert(Data(id = new ObjectId, data = data)).get
    get(id)
  }

  def getAll() = {
    DataDAO
      .find(ref = MongoDBObject())
      .sort(orderBy = MongoDBObject("_id" -> -1))
      .toList
  }

  def get(id: String) = {
    DataDAO.findOneById(new ObjectId(id))
  }

  def get(id: ObjectId) = {
    DataDAO.findOneById(id)
  }

  def delete(id: String) = {
    val dataShouldDelete = get(new ObjectId(id)).get
    DataDAO.remove(dataShouldDelete)
    dataShouldDelete
  }

  def update(newData: Data) = {
    DataDAO.update(MongoDBObject("_id" -> newData.id),
                   MongoDBObject("data" -> newData.data),
                   false,
                   false)
    get(newData.id.toString)
  }
}
