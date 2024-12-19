import React, { useEffect, useRef, useState } from 'react';
import Navbar from './common/NavBar';
import { useLocation, useNavigate } from 'react-router-dom';
import { useTokenContext } from '../contexts/TokenContext';
// import alert from '../assets/alert.wav';

const Dashboard: React.FC = () => {
  // const notificationSound = new Audio(alert);
  const chatPopupRef = useRef<HTMLDivElement>(null);
  const location = useLocation();
  const navigate = useNavigate();
  const { userEmail } = useTokenContext();
  const [isChatOpen, setIsChatOpen] = useState(false);


  useEffect(() => {
    if (!userEmail || userEmail === '') {
      navigate('/login');
    } else {
      // fetchProject(token)
    }
  }, []);


  useEffect(() => {
    if (isChatOpen) {
      document.addEventListener('mousedown', handleClickOutsideChat);
    } else {
      document.removeEventListener('mousedown', handleClickOutsideChat);
    }
    return () => {
      document.removeEventListener('mousedown', handleClickOutsideChat);
    };
  }, [isChatOpen]);

  const handleClickOutsideChat = (event: MouseEvent) => {
    if (chatPopupRef.current && !chatPopupRef.current.contains(event.target as Node)) {
      setIsChatOpen(false);
    }
  };

  return (
    <div className="w-[100%] h-[100vh]">
      <Navbar path={location.pathname} />
      <div className="w-[100%] flex justify-center items-center">
        <div className="mt-5 bg-brown-300 bg-opacity-50 w-[450px] h-[100px] rounded-xl px-10 py-5 shadow-xl flex flex-col gap-4 ">
          {/* <NoteForm token={token} /> */}
        </div>
      </div>
      {/* <button onClick={sendMessage}>send</button> */}
      <div className='w-[100%] flex justify-center items-center'>
        <div className="mt-5 overflow-y-scroll w-[60vw]  min-h-[30vh] max-h-[70vh] rounded-xl px-10 py-5 shadow-xl flex flex-col gap-4 ">
          {/* <NoteList token={token} /> */}
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
