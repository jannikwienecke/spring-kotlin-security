package de.wienecke.basicsecurityapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BasicSecurityAppApplication

fun main(args: Array<String>) {
	runApplication<BasicSecurityAppApplication>(*args)
}
