package com.example.lightweight.data

import android.content.res.Resources
import com.example.lightweight.domain.BodyType

class WorkoutListLocalSource(_resources: Resources) : WorkoutListSource {
    private val resource: Resources = _resources

    override fun getWorkoutListByPart(type: BodyType): List<String> {

//        Arrays.sort(resource.getStringArray(type.getResourceId()))
//        return mutableListOf(resource.getStringArray(type.getResourceId()))
        return resource.getStringArray(type.getResourceId()).toList()
    }
}