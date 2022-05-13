package com.originsdigital.hbssample.groupstanding

enum class StandingsType(val tabName: String, val expanded: Boolean, val solid: Boolean) {
    SOLID_COMPACT(tabName = "Solid Compact", expanded = false, solid = true),
    TRANSPARENT_COMPACT(tabName = "Transparent Compact", expanded = false, solid = false),
    SOLID_EXPANDED(tabName = "Solid Expanded", expanded = true, solid = true),
    TRANSPARENT_EXPANDED(tabName = "Transparent Expanded", expanded = true, solid = false)
}