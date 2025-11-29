import { Link } from "react-router-dom";
import "../App.css";

const Navbar = () => {
    return (
        <nav className="navbar">
            <h2 className="logo">OTP Authentification</h2>
            <ul>
                <li><Link to="/">Envoyer OTP</Link></li>
                <li><Link to="/verify-otp">Vérifier OTP</Link></li>
            </ul>
        </nav>
    );
};

export default Navbar;
