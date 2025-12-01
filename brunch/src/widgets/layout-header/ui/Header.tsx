import menu from '@/assets/hamburger_bar_icon.svg';
import magnifier from '@/assets/magnifier_icon.svg';
import { Link } from 'react-router-dom';

export const Header = () => {
    return (
        <header className='p-7'>
            <div className='flex justify-between'>
                <div className='flex gap-4'>
                    <button className='cursor-pointer' aria-label='메뉴버튼'>
                        <img src={menu} alt='' />
                    </button>
                    <h1 className='text-2xl'>
                        <Link to='/'>brunch 10</Link>
                    </h1>
                </div>
                <div className=''>
                    <div className='flex gap-5'>
                        <button className='cursor-pointer text-gray-300 font-thin border rounded-2xl px-2 py-1'>
                            시작하기
                        </button>
                        <button className='cursor-pointer'>
                            <img src={magnifier} alt='' />
                        </button>
                    </div>
                </div>
            </div>
        </header>
    );
};
