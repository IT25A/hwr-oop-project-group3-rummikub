package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ExampleTest {
	
	@Test
	fun `example test`() {
		// given
		val example = Example()
		// when
		val result = example.sayHelloTo("World")
		// then
		assertThat(result).startsWith("Hello").endsWith("!").contains("World")
	}
}