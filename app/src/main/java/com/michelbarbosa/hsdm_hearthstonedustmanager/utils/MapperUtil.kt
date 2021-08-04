package com.michelbarbosa.hsdm_hearthstonedustmanager.utils

import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.SetWeight
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.Stereotype
import java.util.*

object MapperUtil {
    fun getDefaultStereotype(vararg defaultList: String?): List<Stereotype> {
        val stereotypeNames: List<String> = ArrayList(Arrays.asList(*defaultList))
        val stereotypeList: MutableList<Stereotype> = ArrayList()
        var i = 0
        for (item in stereotypeNames) {
            stereotypeList.add(Stereotype(i, item))
            i++
        }
        return stereotypeList
    }

    fun getDefaultSetWeight(defaultValueAll: Int, vararg defaultList: String?): List<SetWeight> {
        val setWeightNames: List<String> = ArrayList(Arrays.asList(*defaultList))
        val setWeightList: MutableList<SetWeight> = ArrayList()
        var i = 0
        for (item in setWeightNames) {
            setWeightList.add(SetWeight(i, item, defaultValueAll))
            i++
        }
        return setWeightList
    }
}