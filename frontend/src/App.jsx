import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import SendOtpForm from "./Form/SendOtpForm.jsx";
import VerifyOtpForm from "./Form/VerifyOtpForm.jsx";
import Navbar from "./Pages/Navbar";

import "./App.css";

function App() {
    return (
        <Router>
            <Navbar />
            <div style={{ padding: "20px" }}>
                <Routes>
                    <Route path="/" element={<SendOtpForm />} />
                    <Route path="/verify-otp" element={<VerifyOtpForm />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;





