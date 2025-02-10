import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../store/store";
import { setInvoices } from "../store/slices/invoicesSlice";
import axios from "axios";
import { InvoicesTable } from "../components/InvoicesTable";
import Sidebar from "../components/Sidebar";
import Header from "../components/Header";
import InvoiceDetailsModal from "../components/InvoiceDetailsModal";
import { Invoice } from "../types";

const Invoices = () => {
  const dispatch = useDispatch();
  const invoices = useSelector((state: RootState) => state.invoices.invoices);
  const [selectedInvoice, setSelectedInvoice] = useState<Invoice | null>(null);
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);

  useEffect(() => {
    const fetchInvoices = async () => {
      try {
        const response = await axios.get(`http://localhost:3000/invoices?page=${page}&limit=5`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });
        dispatch(setInvoices(response.data.data));
        setTotalPages(response.data.totalPages);
      } catch (error) {
        console.error("Error fetching invoices:", error);
      }
    };

    fetchInvoices();
  }, [dispatch, page]);

  return (
    <div className="flex">
      <Sidebar />
      <div className="flex-1">
        <Header />
        <div className="p-6">
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-2xl font-bold">Invoices</h2>
          </div>

          {invoices.length > 0 && (
            <>
              <InvoicesTable data={invoices} onInvoiceClick={setSelectedInvoice} />

              <div className="flex justify-between items-center mt-6">
                <button
                  disabled={page === 1}
                  onClick={() => setPage((prev) => prev - 1)}
                  className="bg-gray-200 text-gray-700 px-4 py-2 rounded-lg disabled:opacity-50 hover:bg-gray-300"
                >
                  Prev
                </button>
                <p className="text-gray-600">
                  Page <span className="font-semibold">{page}</span> of <span className="font-semibold">{totalPages}</span>
                </p>
                <button
                  disabled={page === totalPages}
                  onClick={() => setPage((prev) => prev + 1)}
                  className="bg-indigo-500 text-white px-4 py-2 rounded-lg hover:bg-indigo-600 disabled:opacity-50"
                >
                  Next
                </button>
              </div>
            </>
          )}
        </div>
      </div>

      {selectedInvoice && (
        <InvoiceDetailsModal
          invoice={selectedInvoice}
          onClose={() => setSelectedInvoice(null)}
        />
      )}
    </div>
  );
};

export default Invoices;
