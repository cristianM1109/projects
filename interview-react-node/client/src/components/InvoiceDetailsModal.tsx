import { Invoice } from "../types";

interface InvoiceDetailsModalProps {
  invoice: Invoice | null;
  onClose: () => void;
}

const InvoiceDetailsModal = ({ invoice, onClose }: InvoiceDetailsModalProps) => {
  if (!invoice) return null; 

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
      <div className="bg-white p-6 rounded-lg shadow-lg w-1/3">
        <h2 className="text-xl font-bold mb-4">Invoice Details</h2>
        <p><strong>Vendor:</strong> {invoice.vendorName}</p>
        <p><strong>Amount:</strong> ${invoice.amount}</p>
        <p><strong>Due Date:</strong> {new Date(invoice.dueDate).toLocaleDateString()}</p>
        <p><strong>Description:</strong> {invoice.description}</p>
        <p><strong>Status:</strong> {invoice.paid ? "Paid ✅" : "Unpaid ❌"}</p>

        <button
          onClick={onClose}
          className="mt-4 w-full bg-red-500 text-white py-2 rounded-lg hover:bg-red-600"
        >
          Close
        </button>
      </div>
    </div>
  );
};

export default InvoiceDetailsModal;
