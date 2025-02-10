import { Invoice } from "../types";

interface InvoicesTableProps {
  data: Invoice[];
  onInvoiceClick: (invoice: Invoice) => void; 
}

export const InvoicesTable = ({ data, onInvoiceClick }: InvoicesTableProps) => {
  return (
    <table className="w-full bg-white rounded-lg shadow-md overflow-hidden">
      <thead className="bg-indigo-500 text-white">
        <tr>
          <th className="px-4 py-2 text-left">Date</th>
          <th className="px-4 py-2 text-left">Payee</th>
          <th className="px-4 py-2 text-left">Description</th>
          <th className="px-4 py-2 text-left">Amount</th>
          <th className="px-4 py-2 text-left">Status</th>
        </tr>
      </thead>
      <tbody>
        {data.map((invoice) => (
          <tr
            key={invoice.id}
            className="border-b hover:bg-gray-100 cursor-pointer"
            onClick={() => onInvoiceClick(invoice)} 
          >
            <td className="px-4 py-2">{new Date(invoice.dueDate).toLocaleDateString()}</td>
            <td className="px-4 py-2">{invoice.vendorName}</td>
            <td className="px-4 py-2">{invoice.description}</td>
            <td className="px-4 py-2">${invoice.amount}</td>
            <td className="px-4 py-2">{invoice.paid ? "Paid ✅" : "Unpaid ❌"}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
