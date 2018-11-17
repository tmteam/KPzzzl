
import java.io.FileReader
import com.esotericsoftware.yamlbeans.YamlReader
import java.io.IOException
import java.nio.file.Files
import java.io.File




fun main(args: Array<String>) {
    val cat = Cat()
    cat.readConfig()
}
class Cat {
    var name: String = ""

        get() = field.toUpperCase()

       public fun readConfig() {
           val fileContent = this.javaClass.getResource("contact.yml").readText()
           // println(fileContent)
           //val reader = YamlReader(FileReader("contact.yml"))

           val reader = YamlReader(fileContent)//FileReader("contact.yml"))
           val contact = reader.read(Contact::class.java)
           println(contact.age)
           for (number in contact.phoneNumbers!!){
               println(number.name)

           }
           println("Hi world")

       }
}

class Contact {
    public var name: String? = null
    public var age: Int = 0
    public var address: String? = null
    public var phoneNumbers: List<Phone>? = null

}

class Phone {
    var name: String? = null
    var number: String? = null
}
