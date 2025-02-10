import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { Invoice } from "../../types"; 

interface InvoicesState {
  invoices: Invoice[];
}

const initialState: InvoicesState = {
  invoices: [],
};

export const invoicesSlice = createSlice({
  name: "invoices",
  initialState,
  reducers: {
    setInvoices: (state, action: PayloadAction<Invoice[]>) => {
      state.invoices = action.payload;
    },
  },
});

export const { setInvoices } = invoicesSlice.actions;
export const invoicesReducer = invoicesSlice.reducer;
