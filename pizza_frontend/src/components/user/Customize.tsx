import React, { useEffect } from 'react';
import Navbar from '../common/NavBar';
import { useNavigate } from 'react-router-dom';
import { useTokenContext } from '../../contexts/TokenContext';
import CustomizePizza from './CustomizePizza';

const Customize: React.FC = () => {
  const navigate = useNavigate();
  const { userEmail } = useTokenContext();

  useEffect(() => {
    if (!userEmail || userEmail === '') {
      navigate('/login');
    } else {
      // fetchSomething(token)
    }
  }, []);

  return (
    <div className="w-[100%] h-[100vh]">
      <Navbar />
      {/* <div className="w-[100%] flex justify-center items-center">
        <div className="mt-5 bg-brown-300 bg-opacity-50 w-[450px] h-[100px] rounded-xl px-10 py-5 shadow-xl flex flex-col gap-4 ">

        </div>
      </div> */}
      <div className='w-[100%] flex justify-center items-center'>
        <div className="mt-5 w-[60vw] min-h-[30vh] rounded-xl px-10 py-5 shadow-xl flex flex-col gap-4 ">
          <CustomizePizza />
        </div>
      </div>
    </div>
  );
};

export default Customize;
