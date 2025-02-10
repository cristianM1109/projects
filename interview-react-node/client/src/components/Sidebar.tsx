import { Home, FileText, Wallet, BarChart3 } from "lucide-react";
import { Link } from "react-router-dom";

const Sidebar = () => {
  return (
    <div className="w-64 min-h-screen bg-gradient-to-b from-blue-200 to-blue-500 p-6 text-white shadow-md">
      <h1 className="text-2xl font-bold mb-6">LOGO</h1>
      <nav className="space-y-4">
        <Link to="/" className="flex items-center gap-3 text-lg hover:text-gray-200">
          <Home size={20} /> Home
        </Link>
        <Link to="/invoices" className="flex items-center gap-3 text-lg font-semibold">
          <FileText size={20} /> Invoices
        </Link>
        <Link to="/bills" className="flex items-center gap-3 text-lg hover:text-gray-200">
          <Wallet size={20} /> Bills
        </Link>
        <Link to="/expenses" className="flex items-center gap-3 text-lg hover:text-gray-200">
          <BarChart3 size={20} /> Expenses
        </Link>
      </nav>
    </div>
  );
};

export default Sidebar;
