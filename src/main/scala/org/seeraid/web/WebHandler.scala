package org.seeraid.web

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

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