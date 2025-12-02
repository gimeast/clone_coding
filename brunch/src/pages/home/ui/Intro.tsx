import { Link } from 'react-router-dom';

const Intro = () => {
    return (
        <section className='px-20 w-full'>
            <h2 className='text-5xl leading-16'>글이 작품이 되는 공간, 브런치</h2>
            <p className='flex flex-col text-4xl leading-12 font-thin'>
                <span className='text-gray-400'>브런치에 담긴 아름다운 작품을 감상해 보세요.</span>
                <span className='text-gray-300'>그리고 다시 꺼내 보세요.</span>
                <span className='text-gray-200'>서랍 속 간직하고 있는 글과 감성을.</span>
            </p>
            <div className='flex justify-end gap-3 mt-7'>
                <span className='text-cyan-300 text-xs'>notice</span>
                <Link to='https://brunch.co.kr/@brunch/395' className='text-sm text-gray-400 font-thin hover:underline'>
                    '첫 달 무료'로 만나는 브런치 작가 멤버십
                </Link>
            </div>
        </section>
    );
};

export default Intro;
