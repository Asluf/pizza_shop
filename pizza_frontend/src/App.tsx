import React from "react";
import './App.css';
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import Login from "./components/common/login";
import Signup from "./components/common/Signup";
import Home from "./components/common/Home";
import Dashboard from "./components/user/Dashboard"
import { TokenProvider } from "./contexts/TokenContext";
import 'react-toastify/dist/ReactToastify.css';
import { PizzaProvider } from "./contexts/PizzaContext";
import Customize from "./components/user/Customize";


const App: React.FC = () => {
  return (
    <TokenProvider>
      <PizzaProvider>
          <Router>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/signup" element={<Signup />} />
              <Route path="/dashboard" element={<Dashboard />} />
              <Route path="/customize" element={<Customize />} />
            </Routes>
          </Router>
      </PizzaProvider>

    </TokenProvider>
  );
};

export default App;
