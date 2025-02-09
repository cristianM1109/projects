import { BadRequestException, Injectable, UnauthorizedException } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { UserService } from '../user/user.service';
import { LoginDto } from './dto/login.dto';

@Injectable()
export class AuthService {
  constructor(
    private readonly userService: UserService,
    private readonly jwtService: JwtService,
  ) {}

  async login(loginDto: LoginDto) {
    const { email, password } = loginDto; 

    if (!email || !password) {
      throw new BadRequestException('Email and password are required');
    }
  
    const user = await this.userService.findByEmail(email); 
    if (!user || user.password !== password) {
      throw new UnauthorizedException('Invalid credentials');
    }
  
    const payload = { email: user.email }; 
    return {
      access_token: this.jwtService.sign(payload),
    };
  }
}
