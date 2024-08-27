import mongoose from "mongoose";
import { userSettingSchema } from "./userSettingsSchema.js";

export const UserSettings = mongoose.model("UserSettings",userSettingSchema,"user_settings")