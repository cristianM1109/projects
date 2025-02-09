import { Test, TestingModule } from '@nestjs/testing';
import { UserService } from './user.service';
import { PrismaService } from '../prisma/prisma.service';

describe('UserService', () => {
  let service: UserService;
  let prisma: PrismaService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        UserService,
        {
          provide: PrismaService,
          useValue: { 
            user: {
              findUnique: jest.fn(), // Mock pentru findUnique
            },
          },
        },
      ],
    }).compile();

    service = module.get<UserService>(UserService);
    prisma = module.get<PrismaService>(PrismaService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  it('should return a user when found', async () => {
    // Simulăm că avem un utilizator în DB
    const mockUser = { id: 1, email: 'test@test.com' };
    prisma.user.findUnique = jest.fn().mockResolvedValue(mockUser);

    const user = await service.findByEmail('test@test.com');

    expect(user).toEqual(mockUser);
    expect(prisma.user.findUnique).toHaveBeenCalledWith({ where: { email: 'test@test.com' } });
  });

  it('should return null if user not found', async () => {
    prisma.user.findUnique = jest.fn().mockResolvedValue(null);

    const user = await service.findByEmail('notfound@test.com');

    expect(user).toBeNull();
    expect(prisma.user.findUnique).toHaveBeenCalledWith({ where: { email: 'notfound@test.com' } });
  });
});
