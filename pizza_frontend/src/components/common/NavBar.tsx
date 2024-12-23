import React from 'react';
import Swal from 'sweetalert2';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { useTokenContext } from '../../contexts/TokenContext';


const Navbar: React.FC = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { setUserEmail, setUserId } = useTokenContext();

  const handleLogout = async () => {
    const { isConfirmed } = await Swal.fire({
      title: 'Logout Confirmation',
      text: 'Are you sure you want to logout?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, logout!'
    });

    if (isConfirmed) {
      try {
        setUserEmail('');
        setUserId('');
        localStorage.clear();
        navigate('/login');
      } catch (error) {
        console.error('Logout failed:', error);
      }
    }
  };

  return (
    <div className="flex w-[100%] px-4 items-center justify-between h-[60px] bg-brown-600">
      <div className="text-2xl text-brown-100 font-bold">Pizza Palette</div>
      <div className="flex justify-between gap-2">
        {location.pathname === '/' ? (
          <>
            <Link
              to="/login"
              className="py-2 px-4 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent bg-brown-800 text-white hover:bg-brown-400"
            >
              Login
            </Link>
            <Link
              to="/signup"
              className="py-2 px-4 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent bg-brown-800 text-white hover:bg-brown-400"
            >
              Signup
            </Link>
          </>
        ) : location.pathname === '/login' ? (
          <>
            <Link
              to="/"
              className="py-2 px-5 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent bg-brown-800 text-white hover:bg-brown-400"
            >
              Home
            </Link>
            <Link
              to="/signup"
              className="py-2 px-4 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent bg-brown-800 text-white hover:bg-brown-400"
            >
              Signup
            </Link>
          </>
        ) : location.pathname === '/signup' ? (
          <>
            <Link
              to="/"
              className="py-2 px-5 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent bg-brown-800 text-white hover:bg-brown-400"
            >
              Home
            </Link>
            <Link
              to="/login"
              className="py-2 px-4 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent bg-brown-800 text-white hover:bg-brown-400"
            >
              Login
            </Link>
          </>
        ) : (location.pathname === '/dashboard' || location.pathname === '/customize' ) ? (
          <>
            <Link
              to="/dashboard"
              className="py-2 px-5 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent bg-brown-800 text-white hover:bg-brown-400"
            >
              Dashboard
            </Link>
            <Link
              to="/customize"
              className="py-2 px-5 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent bg-brown-800 text-white hover:bg-brown-400"
            >
              Customize
            </Link>
            <button
              onClick={handleLogout}
              className="py-2 px-4 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent bg-brown-800 text-white hover:bg-brown-400"
            >
              Logout
            </button>
          </>
        ) : null}

      </div>
    </div>
  );
};

export default Navbar;