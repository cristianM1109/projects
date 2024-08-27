import userSchema from "./userSchema.js";
import mongoose from "mongoose";

export const User = mongoose.model("User", userSchema, "users");
 