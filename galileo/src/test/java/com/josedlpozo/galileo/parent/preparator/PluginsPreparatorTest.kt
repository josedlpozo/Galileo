package com.josedlpozo.galileo.parent.preparator

import android.view.View
import com.josedlpozo.galileo.config.ConfigRepository
import com.josedlpozo.galileo.config.GalileoConfig
import com.josedlpozo.galileo.config.GalileoInternalConfig
import com.josedlpozo.galileo.config.GalileoPlugin
import com.josedlpozo.galileo.items.GalileoItem
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PluginsPreparatorTest {

    @Before
    fun setup() {
        ConfigRepository.internalConfig = GalileoInternalConfig()
        ConfigRepository.more = listOf()
    }

    @Test
    fun `given one plugin, when preparing, then config will have one plugin and more will have zero plugins`() {
        PluginsPreparator.prepare(GalileoConfig(listOf<GalileoPlugin>({ plugin })))

        assertEquals(1, ConfigRepository.internalConfig.plugins.size)
        assertEquals(0, ConfigRepository.more.size)
    }

    @Test
    fun `given five plugins, when preparing, then config will have five plugins and more will have zero plugins`() {
        val plugins = listOf<GalileoPlugin>({ plugin }, { plugin }, { plugin }, { plugin }, { plugin })
        PluginsPreparator.prepare(GalileoConfig(plugins))

        assertEquals(5, ConfigRepository.internalConfig.plugins.size)
        assertEquals(0, ConfigRepository.more.size)
    }

    @Test
    fun `given six plugins, when preparing, then config will have five plugins and more will have two plugins`() {
        val plugins = listOf<GalileoPlugin>({ plugin }, { plugin }, { plugin }, { plugin }, { plugin }, { plugin })
        PluginsPreparator.prepare(GalileoConfig(plugins))

        assertEquals(5, ConfigRepository.internalConfig.plugins.size)
        assertEquals(2, ConfigRepository.more.size)
    }

    private val plugin: GalileoItem = object : GalileoItem {
        override val view: View
            get() = TODO("not implemented")
        override val name: String
            get() = ""
        override val icon: Int
            get() = 0

        override fun snapshot(): String = ""
    }
}