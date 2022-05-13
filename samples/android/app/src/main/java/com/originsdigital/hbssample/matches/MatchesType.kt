package com.originsdigital.hbssample.matches

enum class MatchesType(val tabName: String, val solid: Boolean) {
    SOLID_GROUP_COMPACT(tabName = "Solid Group Compact", solid = true),
    TRANSPARENT_GROUP_COMPACT(tabName = "Transparent Group Compact", solid = false),

    SOLID_ROUND_COMPACT(tabName = "Solid Round Compact", solid = true),
    TRANSPARENT_ROUND_COMPACT(tabName = "Transparent Round Compact", solid = false),

    SOLID_TEAM_COMPACT(tabName = "Solid Team Compact", solid = true),
    TRANSPARENT_TEAM_COMPACT(tabName = "Transparent Team Compact", solid = true)
}