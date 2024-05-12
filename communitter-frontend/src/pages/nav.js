import { useNavigate } from "react-router-dom";
import { useLogout } from "../utilities/auth";
import { useSelector } from "react-redux";

export function Navbar() {
  const logout = useLogout();
  const loggedInUser = useSelector((state) => state.user.loggedInUser);
  const navigate = useNavigate();
  return (
    <nav className="nav">
      <ul className="nav nav-pills">
        <li
          className="nav-item"
          onClick={() => {
            navigate("/home");
          }}
        >
          <button className="nav-link">Home</button>
        </li>
        <li
          className="nav-item"
          onClick={() => {
            navigate("/community/create");
          }}
        >
          <button className="nav-link">Create Community</button>
        </li>
        <li
          className="nav-item"
          onClick={() => {
            navigate(`/user/${loggedInUser.id}`);
          }}
        >
          <button className="nav-link">Profile</button>
        </li>
        <li onClick={logout} className="nav-item">
          <button className="nav-link">Logout</button>
        </li>
      </ul>
    </nav>
  );
}
