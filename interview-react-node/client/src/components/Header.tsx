import { Search, Bell, Settings, Moon, LogOut } from "lucide-react";
import { useNavigate } from "react-router-dom";

const Header = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (
    <div className="flex justify-between items-center p-4 bg-white shadow-md">
      <div className="flex items-center gap-2 text-gray-500">
        <span className="text-gray-600 font-medium">Home / Invoices</span>
      </div>
      <div className="flex items-center gap-4">
        <div className="relative">
          <Search size={20} className="absolute left-3 top-2 text-gray-400" />
          <input
            type="text"
            placeholder="Search"
            className="pl-10 pr-4 py-2 border rounded-lg shadow-sm focus:ring focus:ring-indigo-300"
          />
        </div>
        <Bell size={20} className="text-gray-500 hover:text-indigo-500 cursor-pointer" />
        <Settings size={20} className="text-gray-500 hover:text-indigo-500 cursor-pointer" />
        <Moon size={20} className="text-gray-500 hover:text-indigo-500 cursor-pointer" />
        <img
          src="src/components/pic.png"
          alt="User Avatar"
          className="w-10 h-10 rounded-full border border-gray-300 shadow-sm"
        />
        <button
          onClick={handleLogout}
          className="flex items-center gap-2 bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 transition"
        >
          <LogOut size={20} />
          Logout
        </button>
      </div>
    </div>
  );
};

export default Header;