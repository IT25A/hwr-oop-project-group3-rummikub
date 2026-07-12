package hwr.oop.examples.template.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.web.context.request.ServletWebRequest

class ControllerAdviceTest {
	private val advice = ControllerAdvice()
	
	@Test
	fun `handleGenericException returns internal server error`() {
		val exception = Exception("Something went wrong")
		val request = ServletWebRequest(MockHttpServletRequest())
		
		val response = advice.handleGenericException(
			exception,
			request
		)
		
		assertEquals(
			HttpStatus.INTERNAL_SERVER_ERROR,
			response.statusCode
		)
		
		assertNotNull(response.body)
		
		assertEquals(
			500,
			response.body!!.status
		)
		
		assertEquals(
			"Internal Server Error",
			response.body!!.error
		)
		
		assertEquals(
			"Something went wrong",
			response.body!!.message
		)
	}
	
	@Test
	fun `handleGenericException uses default message when exception message is null`() {
		val exception = Exception()
		val request = ServletWebRequest(MockHttpServletRequest())
		
		val response = advice.handleGenericException(
			exception,
			request
		)
		
		assertEquals(
			500,
			response.body!!.status
		)
		
		assertEquals(
			"Internal Server Error",
			response.body!!.error
		)
		
		assertEquals(
			"An unexpected error occurred",
			response.body!!.message
		)
	}
}