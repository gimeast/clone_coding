import style from './page.module.css';
import 'dayjs/locale/ko';
import ChatItem from '@/app/(afterLogin)/messages/_component/ChatItem';

const Page = () => {
    return (
        <section className={style.container}>
            <h2 className={style.header}>쪽지</h2>
            <ul>
                <ChatItem />
                <ChatItem />
                <ChatItem />
            </ul>
        </section>
    );
};

export default Page;
