package com.originsdigital.hbsonrewindplayerdemo

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

//Add empty glide app module (if app doesn't have any) to activate onrewind LibraryGlideModule.
@GlideModule
class MyAppGlideModule : AppGlideModule() {
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}