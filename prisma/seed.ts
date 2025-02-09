// prisma/seed.ts
import { PrismaClient } from '@prisma/client'

const prisma = new PrismaClient()

async function main() {
  // Crează un utilizator
  const user = await prisma.user.create({
    data: {
      email: 'test1@user.com',
      password: 'password', // Asigură-te că parola este criptată într-un proiect real
      name: 'Test User',
    },
  })

  // Crează facturi pentru utilizator
  await prisma.invoice.createMany({
    data: [
      { vendorName: 'Vendor 1', amount: 100.0, dueDate: new Date('2025-02-10'), description: 'Factură pentru serviciu 1', userId: user.id },
      { vendorName: 'Vendor 2', amount: 250.0, dueDate: new Date('2025-03-15'), description: 'Factură pentru serviciu 2', userId: user.id, paid: true },
      { vendorName: 'Vendor 3', amount: 350.0, dueDate: new Date('2025-03-15'), description: 'Factură pentru serviciu 2', userId: user.id },
      { vendorName: 'Vendor 4', amount: 450.0, dueDate: new Date('2025-03-15'), description: 'Factură pentru serviciu 2', userId: user.id },
      { vendorName: 'Vendor 5', amount: 550.0, dueDate: new Date('2025-03-15'), description: 'Factură pentru serviciu 2', userId: user.id },
      { vendorName: 'Vendor 6', amount: 650.0, dueDate: new Date('2025-03-15'), description: 'Factură pentru serviciu 2', userId: user.id, paid: true },
      { vendorName: 'Vendor 7', amount: 750.0, dueDate: new Date('2025-03-15'), description: 'Factură pentru serviciu 2', userId: user.id, paid: true },
    ],
  })
}

main()
  .catch(e => {
    console.error(e)
    process.exit(1)
  })
  .finally(async () => {
    await prisma.$disconnect()
  })