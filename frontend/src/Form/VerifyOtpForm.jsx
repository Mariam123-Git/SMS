import React, { useState } from "react";
import { verifyOtp } from "../api";
import { useLocation, useNavigate } from "react-router-dom";

const VerifyOtpForm = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const username = location.state?.username;

    const [otp, setOtp] = useState("");
    const [message, setMessage] = useState("");

    if (!username) {
        return (
            <div className="container">
                <p className="error">Aucun utilisateur détecté.</p>
                <button onClick={() => navigate("/")}>Retour</button>
            </div>
        );
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage("");

        try {
            const res = await verifyOtp(username, otp);
            setMessage("Connexion réussie !");
        } catch (err) {
            setMessage("OTP incorrect ou expiré");
        }
    };

    return (
        <div className="container">
            <h1>Vérification OTP</h1>
            <p style={{ textAlign: "center" }}>Pour : <strong>{username}</strong></p>

            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Code OTP"
                    value={otp}
                    onChange={(e) => setOtp(e.target.value)}
                    required
                />

                <button type="submit">Vérifier</button>
            </form>

            {message && (
                <div className={`message ${message.includes("réussie") ? "success" : "error"}`}>
                    {message}
                </div>
            )}
        </div>
    );
};

export default VerifyOtpForm;
