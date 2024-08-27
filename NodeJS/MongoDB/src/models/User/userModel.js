import userSchema from "./userSchema.js";
import mongoose from "mongoose";

const User = mongoose.model("User",userSchema,"users");

export default User;