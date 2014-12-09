package org.seeraid.web

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import java.lang.Long
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid
import org.springframework.web.bind.annotation.RestController
import org.seeraid.sample.SampleConfig
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration

@Controller
@Configuration
@EnableAutoConfiguration
@ComponentScan
class WebHandler {
  @RequestMapping(Array("/"))
  def index = "pages/index"
//  def index = "Hello, Welcome to SeerAid Home page!"
}
object WebHandler {
  def main(args: Array[String]) {
    SpringApplication.run(classOf[WebHandler]);
  }
}