import { Role } from "./role.model";

export class UserDto {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  roles: Role[];
 
  constructor(id: number, username: string, firstName: string, 
    lastName: string, email: string, roles: Role[]) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.roles = roles;
  }
}
