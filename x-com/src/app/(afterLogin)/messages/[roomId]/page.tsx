import style from './page.module.css';
import BackButton from '@/app/(afterLogin)/_component/BackButton';
import { faker } from '@faker-js/faker/locale/ko';
import Image from 'next/image';
import dayjs from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime';
import 'dayjs/locale/ko';
import clsx from 'clsx';

dayjs.extend(relativeTime);
dayjs.locale('ko');

const Page = () => {
    const user = {
        id: 'Elon_Musk',
        name: 'elonmusk',
        image: faker.image.avatar(),
    };

    const messages = [
        {
            messageId: 1,
            roomId: 123,
            id: 'Elon_Musk',
            content: '안녕하세요.',
            createdAt: dayjs(new Date()).format('YYYY년 MM월 DD일 A HH시 mm분'),
        },
        {
            messageId: 2,
            roomId: 123,
            id: 'hero',
            content: '안녕히가세요.',
            createdAt: dayjs(new Date()).format('YYYY년 MM월 DD일 A HH시 mm분'),
        },
    ];

    return (
        <section className={style.container}>
            <div className={style.header}>
                <BackButton />
                {user.name}
            </div>
            <div className={style.userInfo}>
                <Image className={style.profile} src={user.image} alt='프로필' width={50} height={50} />
                <span className={style.name}>{user.name}</span>
                <span className={style.id}>@{user.id}</span>
            </div>
            <ul>
                {messages.map(message => (
                    <li
                        key={message.messageId}
                        className={clsx(message.id === 'Elon_Musk' ? style.myMessage : style.yourMessage)}
                    >
                        <p className={style.content}>{message.content}</p>
                        <span className={style.createdAt}>{message.createdAt}</span>
                    </li>
                ))}
            </ul>
        </section>
    );
};

export default Page;
