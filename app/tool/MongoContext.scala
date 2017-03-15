package tool

/**
  * Created by loui.wang on 2017/3/9.
  */
import play.api.Play
import play.api.Play.current
import salat.{TypeHintFrequency, StringTypeHintStrategy, Context}

package object MongoContext {
  implicit val context = {
    val context = new Context {
      val name = "global"
      override val typeHintStrategy = StringTypeHintStrategy(
        when = TypeHintFrequency.WhenNecessary,
        typeHint = "_t")
    }
    context.registerGlobalKeyOverride(remapThis = "id", toThisInstead = "_id")
    context.registerClassLoader(Play.classloader)
    context
  }
}
