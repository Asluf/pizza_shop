import React, { createContext, useContext, useState, ReactNode, SetStateAction, Dispatch } from "react";
import { Bounce, toast, ToastContainer } from "react-toastify";

interface PizzaContextType {
  // chats: Chat[];
  // setChats: Dispatch<SetStateAction<Chat[]>>;
  showSuccessToast: (body: string) => void;
  showErrorToast: (body: string) => void;
}

const PizzaContext = createContext<PizzaContextType>({} as PizzaContextType);

export const usePizzaContext = () => {
  const context = useContext(PizzaContext);
  if (!context) {
    throw new Error('usePizzaContext must be used within a PizzaProvider');
  }
  return context;
};

type PizzaProviderProps = {
  children: ReactNode;
};

export const PizzaProvider: React.FC<PizzaProviderProps> = ({ children }) => {
  // const [chats, setChats] = useState<Chat[]>([]);

  const showSuccessToast = (body: string) => {
    toast.success(body, {
      position: "bottom-right",
      autoClose: 1500,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "light",
      transition: Bounce,
    });
  };

  const showErrorToast = (body: string) => {
    toast.error(body, {
      position: "bottom-right",
      autoClose: 1500,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "light",
      transition: Bounce,
    });
  };

  const contextValue: PizzaContextType = {
    // chats,
    // setChats,
    showSuccessToast,
    showErrorToast
  };

  return (
    <PizzaContext.Provider value={contextValue}>
      {children}
      <ToastContainer />
    </PizzaContext.Provider>
  );
};