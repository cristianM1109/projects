import { Injectable, NotFoundException } from '@nestjs/common';
import { PrismaService } from '../prisma/prisma.service';

@Injectable()
export class InvoiceService {
  constructor(private prisma: PrismaService) {}

  async findAll(page: number = 1, limit: number = 10) {
    const skip = (page - 1) * limit;

    const invoices = await this.prisma.invoice.findMany({
      skip,
      take: limit,
    });

    const total = await this.prisma.invoice.count();

    return {
      data: invoices,
      total,
      page,
      limit,
      totalPages: Math.ceil(total / limit),
    };
  }

  async findOne(id: string) {
    const invoice = await this.prisma.invoice.findUnique({
      where: { id:Number(id) },
    });

    if (!invoice) {
      throw new NotFoundException(`Invoice with ID ${id} not found`); 
    }

    return invoice;
  }
}
