export interface Invoice {
  id: string;
  vendorName: string; 
  amount: number;
  dueDate: string; 
  description: string;
  userId: string; 
  paid: boolean;
}
