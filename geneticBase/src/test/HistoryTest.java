package test;

import breed.Breeder;
import breed.IBreeder;
import breed.IDManager;
import cull.CullLowest;
import cull.ICuller;
import eval.IEvaluator;
import eval.SpecificStringEvaluator;
import geneticBase.GAController;
import geneticBase.Genome;
import helper.StringToBinary;
import historic.*;
import historic.view.IPresenter;
import historic.view.PresenterController;
import historic.view.StringPreview;
import mutate.BinaryMutator;
import mutate.IMutator;
import mutate.LengthMutator;

import java.util.ArrayList;
import java.util.List;

public class HistoryTest {
	
	public static void main(String[] args) {

		StringToBinary sB = new StringToBinary();
		String target = "Hello";
		HistoryManager manager = new HistoryManager("HistoryTest");

		IBreeder b = new Breeder();
		IMutator m = new BinaryMutator(new LengthMutator(null));
		ICuller c = new CullLowest();
		IEvaluator e = new SpecificStringEvaluator(sB.getBinaryString(target));

		GAController gac = new GAController(b, m, c, e);
		gac.addGene(new Genome(sB.getBinaryString("a"), "", "", IDManager.getId(), -1,-1));
		gac.addGene(new Genome(sB.getBinaryString("a"), "", "", IDManager.getId(), -1, -1));
		gac.setBreedRate(0.5);
		gac.setMaxPop(10);
		gac.setMutationRate(0.05);

		HistoricModel model = new HistoricModel();

		StringPreview previewView = new StringPreview();
		HistoricView overviewView = new HistoricView(model, previewView);

		List<IPresenter> pages = new ArrayList<>();
		pages.add(overviewView);
		pages.add(previewView);
		PresenterController viewController = new PresenterController(pages, overviewView);

		int score = 0;
		Generation lastGen = null;
		Genome best;
		int gen = 0;
		do {
			gac.runCycle();
			best = gac.getBest();
			score = best.Score();
			System.out.println("Gen "+gen+" Best Score is "+score+" Genome: "+ best.toString());

			Generation cur = new Generation(gen);
			cur.add(gac.getPopulation());
			if (lastGen != null) {
				cur.match(lastGen);
			}
			manager.recordGeneration(cur);
			model.add(cur);
			gen++;
			viewController.render();
			lastGen = cur;
		}while(score < target.length()*8);

		System.out.println(sB.getString(best.String()));
	}

}
