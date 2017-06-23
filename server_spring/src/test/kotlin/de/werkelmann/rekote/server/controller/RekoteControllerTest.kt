package de.werkelmann.rekote.server.controller

import de.werkelmann.rekote.server.ServerMain
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(ServerMain::class))
@WebAppConfiguration
class RekoteControllerTest {

    private var mockMvc: MockMvc? = null

    @Autowired
    private val webApplicationContext: WebApplicationContext? = null

    @Before
    fun init() {
        this.mockMvc = webAppContextSetup(webApplicationContext!!).build()
    }

    @Test
    @Throws(Exception::class)
    fun testGetInfo() {
        mockMvc!!.perform(get("/rekote/info/"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("hostName", not(nullValue())))
                .andExpect(jsonPath("ipAddress", not(nullValue())))
    }

}