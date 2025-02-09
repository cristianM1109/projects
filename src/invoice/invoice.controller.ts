import { Controller, Get, Param, Query, UseGuards } from '@nestjs/common';
import { InvoiceService } from './invoice.service';
import { JwtAuthGuard } from '../auth/jwt-auth.guard';

@Controller('invoices')
export class InvoiceController {
  constructor(private invoiceService: InvoiceService) {}

  @UseGuards(JwtAuthGuard)
  @Get()
  async getInvoices(
    @Query('page') page?: number,
    @Query('limit') limit?: number,
  ) {
    return this.invoiceService.findAll(Number(page) || 1, Number(limit) || 10);
  }


  @UseGuards(JwtAuthGuard)
  @Get(':id')
  async getInvoice(@Param('id') id: string) {
    return this.invoiceService.findOne(id);
  }
}
