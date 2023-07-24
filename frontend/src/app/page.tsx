import styles from './page.module.css';
import TokenPage from '@/app/TokenPage';

export default function Home() {
  return (
    <main className={styles.main}>
      <TokenPage></TokenPage>
    </main>
  );
}
