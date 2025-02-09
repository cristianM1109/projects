import { Test, TestingModule } from '@nestjs/testing';
import { InvoiceService } from './invoice.service';
import { PrismaService } from '../prisma/prisma.service';
import { NotFoundException } from '@nestjs/common';

describe('InvoiceService', () => {
  let service: InvoiceService;
  let prisma: PrismaService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        InvoiceService,
        {
          provide: PrismaService,
          useValue: {
            invoice: {
              findMany: jest.fn().mockResolvedValue([
                { id: 1, amount: 100 },
                { id: 2, amount: 200 },
              ]),
              findUnique: jest.fn(),
              count: jest.fn().mockResolvedValue(10),
            },
          },
        },
      ],
    }).compile();

    service = module.get<InvoiceService>(InvoiceService);
    prisma = module.get<PrismaService>(PrismaService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  it('should return all invoices with pagination', async () => {
    const result = await service.findAll(1, 2);

    expect(result.data.length).toBeGreaterThan(0);
    expect(result.total).toBe(10);
    expect(result.page).toBe(1);
    expect(result.limit).toBe(2);
    expect(result.totalPages).toBe(5);

    expect(prisma.invoice.findMany).toHaveBeenCalledWith({ skip: 0, take: 2 });
    expect(prisma.invoice.count).toHaveBeenCalled();
  });

  it('should return a single invoice when found', async () => {
    const mockInvoice = { id: 1, amount: 100 };
    prisma.invoice.findUnique = jest.fn().mockResolvedValue(mockInvoice);

    const result = await service.findOne('1');

    expect(result).toEqual(mockInvoice);
    expect(prisma.invoice.findUnique).toHaveBeenCalledWith({
      where: { id: 1 },
    });
  });

  it('should throw NotFoundException when invoice is not found', async () => {
    prisma.invoice.findUnique = jest.fn().mockResolvedValue(null);

    await expect(service.findOne('999'))
      .rejects
      .toThrow(NotFoundException);

    expect(prisma.invoice.findUnique).toHaveBeenCalledWith({
      where: { id: 999 },
    });
  });
});
