import { FullName } from "./fullName";
import { Gender } from "./gender.num";


export interface Registration{
    id:string;
    fullName:FullName;
    email:string;
    gender:Gender;
    country:string;
}