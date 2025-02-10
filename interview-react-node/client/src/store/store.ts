import { configureStore } from "@reduxjs/toolkit";
import { invoicesReducer } from "./slices/invoicesSlice";
import { authReducer } from "./slices/authSlice";

export const store = configureStore({
  reducer: {
    invoices: invoicesReducer,
    auth: authReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
