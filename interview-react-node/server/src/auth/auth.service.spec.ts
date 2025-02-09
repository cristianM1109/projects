import { Test, TestingModule } from '@nestjs/testing';
import { AuthService } from './auth.service';
import { JwtService } from '@nestjs/jwt';
import { UserService } from '../user/user.service';

describe('AuthService', () => {
  let authService: AuthService;
  let userService: UserService;
  let jwtService: JwtService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        AuthService,
        {
          provide: UserService,
          useValue: { findByEmail: jest.fn() },
        },
        {
          provide: JwtService,
          useValue: { sign: jest.fn().mockReturnValue('test-token') },
        },
      ],
    }).compile();

    authService = module.get<AuthService>(AuthService);
    userService = module.get<UserService>(UserService);
    jwtService = module.get<JwtService>(JwtService);
  });

  it('should be defined', () => {
    expect(authService).toBeDefined();
  });

  it('should return a token when login is successful', async () => {
    const mockUser = { id: 1, email: 'test@example.com', password: 'password' };
    userService.findByEmail = jest.fn().mockResolvedValue(mockUser);

    const result = await authService.login({ email: 'test@example.com', password: 'password' });

    expect(result).toEqual({ access_token: 'test-token' });
    expect(jwtService.sign).toHaveBeenCalled();
  });

  it('should throw an error if login fails', async () => {
    userService.findByEmail = jest.fn().mockResolvedValue(null);

    await expect(authService.login({ email: 'test@example.com', password: 'password' }))
      .rejects
      .toThrow('Invalid credentials');
  });
});
