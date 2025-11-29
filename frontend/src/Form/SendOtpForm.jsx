import React, { useState } from "react";
import { sendOtp } from "../api";
import { useNavigate } from "react-router-dom";

const SendOtpForm = () => {
    const [username, setUsername] = useState("");
    const [message, setMessage] = useState("");
    const [loading, setLoading] = useState(false);

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setMessage("");

        try {
            const res = await sendOtp(username);

            setMessage("OTP envoyé !");
            setTimeout(() => {
                navigate("/verify", { state: { username } });
            }, 1000);

        } catch (err) {
            setMessage("Erreur : " + (err.response?.data || "Serveur indisponible"));
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="container">
            <h1>Connexion OTP</h1>

            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Nom d'utilisateur"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                />

                <button type="submit" disabled={loading}>
                    {loading ? "Envoi..." : "Envoyer OTP"}
                </button>
            </form>

            {message && (
                <div className={`message ${message.includes("Erreur") ? "error" : "success"}`}>
                    {message}
                </div>
            )}
        </div>
    );
};

export default SendOtpForm;
